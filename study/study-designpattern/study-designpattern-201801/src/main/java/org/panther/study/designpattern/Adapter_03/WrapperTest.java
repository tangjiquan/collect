package org.panther.study.designpattern.Adapter_03;

/**
 * @Author: Kevin
 * @Description:
 * 接口的适配器是这样的：有时我们写的一个接口中有多个抽象方法，当我们写该接口的实现类时，必须实现该接口的所有方法，这明显有时比较浪费，因为并不是所有的方法都是我们需要的，有时只需要某一些，此处为了解决这个问题，我们引入了接口的适配器模式，借助于一个抽象类，该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，只和该抽象类取得联系，所以我们写一个类，继承该抽象类，重写我们需要的方法就行。
 *
 * @Date: Created in 上午12:30 18-3-29
 * @Version:
 *
 *
 * 三种适配器模式的应用场景：
类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。


 */
public class WrapperTest {

    public static void main(String[] args){
        SourceSub1 sub1 = new SourceSub1();
        SourceSub2 sub2 = new SourceSub2();

        sub1.method1();
        sub1.method2();
        sub2.method1();
        sub2.method2();
    }
}
