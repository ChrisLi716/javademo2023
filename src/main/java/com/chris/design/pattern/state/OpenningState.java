package com.chris.design.pattern.state;

public class OpenningState extends LiftState{
    @Override
    public void open() {
        System.out.println("电梯门已经打开了！");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSING_STATE);
        super.context.getLiftState().close();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
