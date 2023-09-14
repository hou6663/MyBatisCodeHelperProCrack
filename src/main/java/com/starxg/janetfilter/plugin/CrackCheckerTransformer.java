package com.starxg.janetfilter.plugin;

import com.janetfilter.core.plugin.MyTransformer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.security.ProtectionDomain;

public class CrackCheckerTransformer implements MyTransformer {
    @Override
    public String getHookClassName() {
        return "com/ccnode/codegenerator/aa/l/I";
    }


    @Override
    public byte[] transform(ClassLoader loader, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, String className, byte[] classBytes, int order) throws Exception {
        final ClassPool pool = ClassPool.getDefault();
        final CtClass clazz = pool.makeClass(new ByteArrayInputStream(classBytes));

        final CtMethod method = clazz.getDeclaredMethod("run");
        method.insertBefore("if(true)return;");

        classBytes = clazz.toBytecode();

        clazz.detach();

        return classBytes;
    }


}
