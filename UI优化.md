# UI优化
## RelativeLayout和LinearLayout性能分析
* 1.RelativeLayout会让子View调用2次onMeasure，LinearLayout 在有weight时，也会调用子View2次onMeasure
* 2.RelativeLayout的子View如果高度和RelativeLayout不同，则会引发效率问题，当子View很复杂时，这个问题会更加严重。如果可以，尽量使用padding代替margin。

## RenderThread
* 5.1引入了renderthread线程，可以讲draw操作从UIThread解放出来，这样做的好处是，UIThread将绘制指令sync给renderthread以后可以继续执行measure/layout操作，非常有利于提升设备操作体验
![](./renderThread.jpg)
* 用RenderThread执行动画,使用条件
	* 支持硬件加速
    * 不设置任何动画回调
    * ViewPropertyAnimatorRT 不为null，需要反射

![](./ViewPropertyAnimatorRT1.png)

![](./ViewPropertyAnimatorRT2.png)

![](./ViewPropertyAnimatorRT3.png)    
## RenderScript
## 调试
    查询当前屏幕界面所在(对应)的Activity
    inspect 当前正在调试的前台APP的UI层级和信息
    dump当前屏幕界面的UI树层级信息(pull之后打开对应的xml即可查看)
    属性动画，可以在开发者选项中调整大小和时长缩放，比如加大时长缩放值，则可以以类似慢镜头的方式查看动画效果
