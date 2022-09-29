# mjswing
mjswing是一个Java Swing的美化包，包含了各种组件，包括Button、PrograssBar、Window、ImagePanel等组件

## 部分组件
#### MJButton
一个可以实现多种动画效果的按钮
**使用方法:** `MJButton button = new MJButton(panel);` 参数为父类控件

默认情况下的按钮：

![](https://cdn.jsdelivr.net/gh/Mizhou-NJJ/mjswing/source/rmimg/mjb0.png)

**调用方法`setBorderRadius(int radiu)`**用来设置边框弧度
![](https://cdn.jsdelivr.net/gh/Mizhou-NJJ/mjswing/source/rmimg/mjb1.png)

**方法`transition、transitionBgColorTo、transitionBorderColorTo、transitionFontColorTo`均可以产生类似CSS3的过渡动画效果**

**其它**

请看代码注释及其文档说明


#### LoadingPanel

`LoadingPanel`用来放置进度条、加载页面等组件
**使用方法:** `LoadingPanel loadingPanel =new LoadingPanel();`

+ **LineMessageAlert**

  一个动态消息进度条

  ```Java
  LoadingPanel loadingPanel =new LoadingPanel();
  UpLoadingStyle us = new LineMessageAlert();
  loadingPanel.setLoadingStyle(us);
  new Thread(new Runnable() {
      @Override
      public void run() {
          int i=0;
          while (i<=100){
              us.updateProgress(i++);
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }).start();
  container.add(loadingPanel);
  loadingPanel.loading();
  ```
**演示**:
![](https://cdn.jsdelivr.net/gh/Mizhou-NJJ/mjswing/source/rmimg/m0.png)

+ **TikNote**

  动态跳动的音符加载面板

  ```Java
  LoadingStyle style  = new TikNote();
  loadingPanel.setLoadingStyle(style);
  loadingPanel.loading();
  ```
**演示:**
![](https://cdn.jsdelivr.net/gh/Mizhou-NJJ/mjswing/source/rmimg/m1.png)


+ **HollowTextLine**

  带文字的条形进度条

  ```Java
  UpLoadingStyle us = new HollowTextLine();
          loadingPanel.setLoadingStyle(style);
          new Thread(new Runnable() {
              @Override
              public void run() {
                  int i=0;
                  while (i<=100){
                      us.updateProgress(i++);
                      try {
                          Thread.sleep(100);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          }).start();
          loadingPanel.loading();
  ```

  **演示:**

  ![](https://cdn.jsdelivr.net/gh/Mizhou-NJJ/mjswing/source/rmimg/m2.png)

+ **更多的加载类型组件**

  包括`EasyConnecting、EasyLine、EasyProgressCircle、EasyShadowConnecting、EasyWaitCircle、HHollowLine`等可以查看代码注释及其文档

## 其它组件
更多组件包括`GradientPanel、ImageFragmentPanel、RoundPanel、EnvisageShadow、MJDialog等可以通过文档来查阅使用




