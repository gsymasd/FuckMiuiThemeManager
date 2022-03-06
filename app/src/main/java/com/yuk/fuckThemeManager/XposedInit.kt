package com.yuk.fuckThemeManager

import androidx.annotation.Keep
import de.robv.android.xposed.*
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam
import de.robv.android.xposed.callbacks.XC_LoadPackage
import miui.drm.DrmManager

class XposedInit : IXposedHookLoadPackage, IXposedHookZygoteInit {

    override fun initZygote(startupParam: StartupParam) {
        try {
            XposedBridge.hookAllMethods(
                DrmManager::class.java, "isLegal",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.result = true
                    }
                })
        } catch (t: Throwable) {
            XposedBridge.log(t)
        }
        try {
            XposedBridge.hookAllMethods(
                DrmManager::class.java, "isPermanentRights",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.result = true
                    }
                })
        } catch (t: Throwable) {
            XposedBridge.log(t)
        }
        try {
            XposedBridge.hookAllMethods(
                DrmManager::class.java, "isRightsFileLegal",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.result = true
                    }
                })
        } catch (t: Throwable) {
            XposedBridge.log(t)
        }
        try {
            XposedBridge.hookAllMethods(
                DrmManager::class.java, "isSupportAd",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.result = false
                    }
                }
            )
        } catch (t: Throwable) {
            XposedBridge.log(t)
        }
        try {
            XposedBridge.hookAllMethods(
                DrmManager::class.java, "setSupportAd",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        param.args[1] = true
                    }
                }
            )
        } catch (t: Throwable) {
            XposedBridge.log(t)
        }
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.android.thememanager" -> {
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.e.a.d",
                        lpparam.classLoader,
                        "a",
                        "com.android.thememanager.basemodule.resource.model.Resource",
                        object : XC_MethodHook() {
                            override fun beforeHookedMethod(param: MethodHookParam) {
                                param.result = DrmManager.DrmResult.DRM_SUCCESS
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.Resource",
                        lpparam.classLoader,
                        "isAuthorizedResource",
                        XC_MethodReplacement.returnConstant(true)
                    )
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.Resource",
                        lpparam.classLoader,
                        "isProductBought",
                        object : XC_MethodHook() {
                            override fun beforeHookedMethod(param: MethodHookParam) {
                                param.result = true
                            }
                        })
                } catch (t: Throwable) {
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.Resource",
                        lpparam.classLoader,
                        "setProductBought",
                        Boolean::class.java, object : XC_MethodHook() {
                            override fun beforeHookedMethod(param: MethodHookParam) {
                                param.args[0] = true
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.Resource",
                        lpparam.classLoader,
                        "setProductPrice",
                        Int::class.java,
                        object : XC_MethodHook() {
                            override fun beforeHookedMethod(param: MethodHookParam) {
                                param.args[0] = 0
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.Resource",
                        lpparam.classLoader,
                        "getProductPrice",
                        object : XC_MethodHook() {
                            override fun afterHookedMethod(param: MethodHookParam) {
                                param.result = 0
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.ResourceOnlineProperties",
                        lpparam.classLoader,
                        "setProductPrice",
                        Int::class.java,
                        object : XC_MethodHook() {
                            override fun beforeHookedMethod(param: MethodHookParam) {
                                param.args[0] = 0
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.ResourceOnlineProperties",
                        lpparam.classLoader,
                        "getProductPrice",
                        object : XC_MethodHook() {
                            override fun afterHookedMethod(param: MethodHookParam) {
                                param.result = 0
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.ResourceOnlineProperties",
                        lpparam.classLoader,
                        "isProductBought",
                        object : XC_MethodHook() {
                            override fun afterHookedMethod(param: MethodHookParam) {
                                param.result = true
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.basemodule.resource.model.ResourceOnlineProperties",
                        lpparam.classLoader,
                        "setProductBought",
                        Boolean::class.java,
                        object : XC_MethodHook() {
                            override fun beforeHookedMethod(param: MethodHookParam) {
                                param.args[0] = true
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
                try {
                    XposedHelpers.findAndHookMethod(
                        "com.android.thememanager.detail.theme.model.OnlineResourceDetail",
                        lpparam.classLoader,
                        "toResource",
                        object : XC_MethodHook() {
                            override fun afterHookedMethod(param: MethodHookParam) {
                                XposedHelpers.setObjectField(param.thisObject, "productPrice", 0)
                                XposedHelpers.setObjectField(param.thisObject, "bought", true)
                            }
                        })
                } catch (t: Throwable) {
                    XposedBridge.log(t)
                }
            }
            else -> return
        }
    }
}