package org.panther.study.designpattern.Facade_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午1:15 18-3-29
 * @Version:
 */
public class Computer {

    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer(){
        this.cpu = new CPU();
        this.memory = new Memory();
        this.disk = new Disk();
    }

    public void startup(){
        System.out.println("startup the computer");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("startup computer finished");
    }

    public void shutdown(){
        System.out.println("shutdown the computer");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("shutdown computer finished");
    }


}
