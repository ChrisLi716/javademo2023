## 1 策略模式是什么
> 策略模式，应该是工作中比较常用的设计模式，
将一些除了过程不同其他都一样的函数封装成策略，然后调用方自己去选择想让数据执行什么过程策略

> 调用方自己选择用哪一种策略完成对数据的操作,也就是`一个类的行为或其算法可以在运行时更改`

## 2 策略模式三种实现
### 2.1 经典策略模式

### 2.2 基于枚举的策略模式
> 基于枚举的策略模式优雅许多，调用方也做到了0修改，但正确地使用枚举策略模式需要额外考虑以下几点。
> 1. 枚举的策略类是公用且静态，这意味着这个策略过程不能引入非静态的部分，扩展性受限
> 2. 策略模式的目标之一，是优秀的扩展性和可维护性，最好能新增或修改某一策略类时，对其他类是无改动的。而枚举策略如果过多或者过程复杂，维护是比较困难的，可维护性受限

### 2.3 基于工厂的策略模式