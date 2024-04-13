#include <jvmti.h>
#include <android/log.h>
#include <string.h>

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
        
        if (strcmp(signature, "Lcom/example/hello/MainActivity;") == 0) {
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

    if(strcmp(name, "com/example/hello/MainActivity")==0){
        LOGI("[Agent] ClassFileLoadHook: %s", name);
    }else{
        return;
    }

    LOGI("[Agent] class_data_len: %d", class_data_len);
    // // unsigned char* c = nullptr
    for(int i=0; i<class_data_len; i+=2){
        LOGI("[Agent] i=%d: %02x, %02x", i, class_data[i], class_data[i+1]);
    }
    LOGI("[Agent] class_data_len: %d", class_data_len);

    int d8_idx = 526;
    for(int i=d8_idx; i<d8_idx+4; i++){
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
    // (*new_class_data)[529] = '\x02';
    // *new_class_data_len = class_data_len;
    LOGI("[Agent] ClassFileloadHook returning");

}