void JNICALL ClassFileLoadHook(jvmtiEnv *jvmti_env, JNIEnv* jni_env,
            jclass class_being_redefined, jobject loader, const char* name,
            jobject protection_domain, 
            jint class_data_len, const unsigned char* class_data,
            jint* new_class_data_len, unsigned char** new_class_data);

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

    ///////////////////////////////////////////////////////////////

    jvmtiEventCallbacks callbacks;
    memset(&callbacks, 0, sizeof(callbacks));
    callbacks.jvmtiEventClassFileLoadHook = &ClassFileLoadHook; 
    jvmti->SetEventCallbacks(&callbacks, sizeof(callbacks));
    LOGI("[Agent] callback registered, enabling class file load hook");

    jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK , NULL);
    // thread is NULL, should enable globally
    LOGI("[Agent] enabled class load notification");

    if(err != JVMTI_ERROR_NONE){
        LOGE("[Agent] enable class load hook error %d", err);
    }

    ////////////////////////////////////////////////////////////////

    jclass* classes; jint class_count;
    unsigned int found = 0; jvmtiError err = JVMTI_ERROR_NONE;
    err = jvmti->GetLoadedClasses(&class_count, &classes);
    LOGI("[Agent] got loaded classes");

    jclass* clazz = classes;
    for (int i = 0; i < class_count; i++) {
        char* signature; 
        err = jvmti->GetClassSignature(classes[i], &signature, NULL);
        
        if (strcmp(signature, "Lcom/example/hello_0/MainActivity;") == 0) {
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
