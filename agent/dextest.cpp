#include <jvmti.h>
#include <android/log.h>
#include <string.h>
#include <jni.h>

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "Agent", __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, "Agent", __VA_ARGS__))
#define TYPE_INT 1
#define TYPE_DBL 2
#define TYPE_OBJ 3
#define TYPE_STR 4

void breakpointCallback(jvmtiEnv *jvmti_env, JNIEnv* jni_env,
     jthread thread, jmethodID method, jlocation location) ;

void JNICALL ClassFileLoadHook(jvmtiEnv *jvmti_env, JNIEnv* jni_env, jclass class_being_redefined, jobject loader, const char* name, jobject protection_domain,
            jint class_data_len, const unsigned char* class_data, jint* new_class_data_len, unsigned char** new_class_data) ;

JNIEXPORT jint JNICALL Agent_OnAttach(JavaVM* vm, char* options, void* reserved){
    LOGI("[Agent] Agent OnAttach");
    jvmtiError err;
    // get jvmti environment
    jvmtiEnv *jvmti;
    vm->GetEnv(reinterpret_cast<void**>(&jvmti), JVMTI_VERSION_1_2);
    LOGI("[Agent] got environment, adding capability");

    jvmtiCapabilities capabilities; 
    err = jvmti->GetPotentialCapabilities(&capabilities);
    LOGI("[Agent] got capabilities");
    // beter practice to not add all capabilities later 
    err = jvmti->AddCapabilities(&capabilities);
    LOGI("[Agent] added capability, registering callback");

    jvmtiEventCallbacks callbacks;
    memset(&callbacks, 0, sizeof(callbacks));
    callbacks.ClassFileLoadHook = &ClassFileLoadHook; // &breakpointCallback is function pointer
    jvmti->SetEventCallbacks(&callbacks, sizeof(callbacks));
    LOGI("[Agent] callback registered, enabling class file load hook");

    jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
    // thread is NULL, should enable globally
    LOGI("[Agent] event notification enabled");

    if(err != JVMTI_ERROR_NONE){
        LOGE("[Agent] enable class load hook error %d", err);
    }

    ///////////////////////////////////////////////////////////////

    jclass* classes; jint class_count;
    unsigned int found = 0; 
    err = jvmti->GetLoadedClasses(&class_count, &classes);
    LOGI("[Agent] got loaded classes");

    jclass* clazz = classes;
    char* signature = nullptr; 
    for (int i = 0; i < class_count; i++) {
        
        err = jvmti->GetClassSignature(classes[i], &signature, NULL);
        
        if (strcmp(signature, "Lcom/example/dextest/MainActivity;") == 0) {
            LOGI("[Agent] found class: %s", signature);
            found = 1;
            clazz = classes + i;
            break;
        }
        jvmti->Deallocate((unsigned char*)signature);
    }
    if(found == 0){
        LOGE("[Agent] class not found: %s", signature);
        return JVMTI_ERROR_ABSENT_INFORMATION;
    }

    err = jvmti->RetransformClasses(1, clazz);

    if(err != JVMTI_ERROR_NONE){
        LOGE("[Agent] retransform class error %d", err);
    }

    LOGI("[Agent] returning");

    return JNI_OK;
}

void JNICALL ClassFileLoadHook(jvmtiEnv *jvmti_env, JNIEnv* jni_env, jclass class_being_redefined, jobject loader, const char* name, jobject protection_domain,
            jint class_data_len, const unsigned char* class_data, jint* new_class_data_len, unsigned char** new_class_data){

    if(strcmp(name, "com/example/dextest/MainActivity")==0){
        LOGI("[Agent] ClassFileLoadHook: %s", name);
    }else{
        return;
    }

    LOGI("[Agent] class_data_len: %d", class_data_len);
    // // unsigned char* c = nullptr
    // for(int i=0; i<class_data_len; i+=2){
    //     LOGI("[Agent] i=%d: %02x, %02x", i, class_data[i], class_data[i+1]);
    // } LOGI("[Agent] class_data_len: %d", class_data_len);

    int start_idx = 0; int len = 12;
    for(int i=start_idx; i<start_idx+len; i++){
        LOGI("[Agent] i=%d: %02x", i, class_data[i]);
    }
    // LOGI("[Agent] i=%d: %02x", 530, class_data[530]);
    // LOGI("[Agent] i=%d: %02x", 531, class_data[531]);
    // LOGI("[Agent] i=%d: %02x", 532, class_data[532]);
    // LOGI("[Agent] i=%d: %02x", 529, class_data[533]);

    
    // unsigned char* modified_bytecode = NULL;
    // jvmtiError err = jvmti_env->Allocate(class_data_len, new_class_data);
    // if (err != JVMTI_ERROR_NONE) {
    //     LOGE("[Agent] error allocating");
    // }
    // memcpy(*new_class_data, class_data, class_data_len);
    // // checksum
    // (*new_class_data)[8] = '\x0f';
    // (*new_class_data)[9] = '\x59';
    // (*new_class_data)[10] = '\x4b';
    // (*new_class_data)[11] = '\x03';

    // (*new_class_data)[529] = '\x02';
    // *new_class_data_len = class_data_len;

    // Open the file containing the modified bytecode
    jstring file_path = jni_env->NewStringUTF("/data/data/com.example.dextest/files/classes3_d.dex");
    
    jclass file_class = jni_env->FindClass("java/io/File");
    jmethodID file_constructor = jni_env->GetMethodID(file_class, "<init>", "(Ljava/lang/String;)V");
    jobject file_object = jni_env->NewObject(file_class, file_constructor, file_path);
    jmethodID file_length = jni_env->GetMethodID(file_class, "length", "()J");
    jlong file_size = jni_env->CallLongMethod(file_object, file_length);

    // Allocate memory for the modified bytecode
    jvmtiError err = jvmti_env->Allocate(file_size, (unsigned char**)new_class_data);
    if (err != JVMTI_ERROR_NONE) {
        LOGE("[Agent] Error allocating memory for modified bytecode");
        jni_env->DeleteLocalRef(file_path);
        jni_env->DeleteLocalRef(file_class);
        jni_env->DeleteLocalRef(file_object);
        return;
    }

    // Open the FileInputStream
    jclass file_input_stream_class = jni_env->FindClass("java/io/FileInputStream");
    jmethodID file_input_stream_constructor = jni_env->GetMethodID(file_input_stream_class, "<init>", "(Ljava/io/File;)V");
    jobject file_input_stream_object = jni_env->NewObject(file_input_stream_class, file_input_stream_constructor, file_object);

    // Read the file
    jmethodID read_method = jni_env->GetMethodID(file_input_stream_class, "read", "([B)I");
    jbyteArray byte_array = jni_env->NewByteArray(file_size);
    jni_env->CallIntMethod(file_input_stream_object, read_method, byte_array);

    // Copy the bytecode from the Java byte array to the native buffer
    jni_env->GetByteArrayRegion(byte_array, 0, file_size, (jbyte*)*new_class_data);

    // Set the new class data length
    *new_class_data_len = file_size;

    // Clean up
    jni_env->DeleteLocalRef(file_path);
    jni_env->DeleteLocalRef(file_class);
    jni_env->DeleteLocalRef(file_object);
    jni_env->DeleteLocalRef(file_input_stream_class);
    jni_env->DeleteLocalRef(file_input_stream_object);
    jni_env->DeleteLocalRef(byte_array);

    LOGI("[Agent] ClassFileLoadHook returning");

}

// void JNICALL ClassFileLoadHook(jvmtiEnv *jvmti_env, JNIEnv* jni_env, jclass class_being_redefined, jobject loader, const char* name, jobject protection_domain,
//             jint class_data_len, const unsigned char* class_data, jint* new_class_data_len, unsigned char** new_class_data) {


//     // Open the file containing the modified bytecode
//     FILE* file = fopen("/sdcard/modified_class.dex", "rb");
//     if (file == nullptr) {
//         LOGE("[Agent] Failed to open modified_class.dex");
//         return;
//     }

//     // Get the file size
//     fseek(file, 0, SEEK_END);
//     long file_size = ftell(file);
//     rewind(file);

//     // Allocate memory for the modified bytecode
//     jvmtiError err = jvmti_env->Allocate(file_size, (unsigned char**)new_class_data);
//     if (err != JVMTI_ERROR_NONE) {
//         LOGE("[Agent] Error allocating memory for modified bytecode");
//         fclose(file);
//         return;
//     }

//     // Read the modified bytecode from the file
//     size_t bytes_read = fread(*new_class_data, 1, file_size, file);
//     if (bytes_read != file_size) {
//         LOGE("[Agent] Error reading modified bytecode from file");
//         jvmti_env->Deallocate(*new_class_data);
//         fclose(file);
//         return;
//     }

//     // Set the new class data length
//     *new_class_data_len = file_size;

//     fclose(file);
//     LOGI("[Agent] ClassFileLoadHook returning");
// }