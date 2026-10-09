# 初音主题适配（第一版）

基于 MeloX-Android main 与 Twilight-Echo-plugins/miku-navigation 0.9.4 的视觉规范，独立实现 Jetpack Compose 主题；不执行原插件 JS/CSS。

## 已实现

- 设置 → 常规 → 主题风格：默认 / 初音未来；与跟随系统、浅色、深色独立组合。
- theme_style 偏好持久化、首帧初始化、立即重组及未知值回退。
- 源主题青绿、奶白、墨青、粉色 Material 3 色板；保留宿主危险操作颜色。
- 底部玻璃导航的深浅色插值使用当前主题，避免底栏回到默认蓝色。
- 可选首页坐姿、音乐库蓝天、其他根页抱花背景。图片固定在根背景层，不随列表行重复解码；背景参与现有玻璃 backdrop。
- 七个根导航入口可选原色图标；搜索没有对应源图标，保留宿主图标。下载图标使用源“导入歌曲”图标作为近似映射。
- 未导入素材时仍可编译使用：青绿渐变背景 + 现有图标。
- 新增色值、对比度、错误语义 JUnit 测试。

## 应用代码补丁

在原项目根目录运行：

```sh
git apply --check /path/to/melox-miku-theme.patch
git apply /path/to/melox-miku-theme.patch
```

若上游文件已变化，请先检查冲突。补丁不含 APK、上游仓库及第三方图片。

## 可选素材导入

原插件 LICENSE 标注 LicenseRef-Miku-Navigation-Preview。仓库公开可读取不等于图片、角色与图标可再分发；根 Apache-2.0 许可不能直接覆盖这些素材。取得适用权限后，从你本地插件目录导入：

```sh
python3 tools/import_miku_assets.py /path/to/Twilight-Echo-plugins/plugins/miku-navigation --confirm-rights
```

该参数仅是操作确认，不提供任何授权。脚本复制源 LICENSE、THIRD_PARTY_NOTICES 和 manifest，保留原始文件，不移除签名。未确认授权时请不要发布带这些素材的源码、APK 或宣传内容。

删除导入素材：

```sh
python3 tools/import_miku_assets.py unused --remove
```

## 验证与已知边界

当前 Minis 环境无 JDK / Android SDK 37，因此**未执行 Android 编译、JUnit 或真机验证**。已做补丁空白检查、XML 解析、导入工具的沙盒测试和色板对比度计算。请在正式 Android 开发环境运行：

```sh
cd android
./gradlew :app:assembleDebug :app:testDebugUnitTest
```

验收浅/深/系统 × 默认/初音，切换后底栏、设置控件、重启保持、无素材回退；手机和平板都检查文本和专辑封面。

这是第一版根页面适配，并非原插件全部功能移植：尚未替换各设置分类图标、空状态插画、收藏按钮的硬编码颜色；独立详情页及全屏播放器保留自身不透明封面/背景，不强制覆盖。首页图像目前放在根背景而非专门的推荐卡片；Compose 手机裁切采用 CenterEnd，不宣称像素级复刻桌面 CSS。

源项目：
- https://github.com/lladlam/MeloX-Android
- https://github.com/Px-asen/Twilight-Echo-plugins/tree/main/plugins/miku-navigation
