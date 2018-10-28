package com.panther.study.jdk.java.util;

/**
 * 模拟jdk中的ArrayList
 *
 * @author: Kevin
 * @date: created in 下午3:40 2018-09-16
 * @version: V1.0
 */
public class ArrayList<E> {

	private int size;
	private Object[] elementData;
	private Object[] emptyData = {};
	private int initSIZE = 10;


	public ArrayList(){
		super();
		this.elementData = emptyData;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public boolean contains(Object o){
		return indexOf(o) >= 0;
	}

	public int indexOf(Object o){
		if(o == null){
			for(int i=0; i<size; i++){
				if(elementData[i] == null){
					return i;
				}
			}
		}else{
			for(int i=0; i<size; i++){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}


	public E get(int index){
		rangeCheck(index);
		return elementData(index);
	}

	E elementData(int index){
		return (E)elementData[index];
	}


	public E set(int index, E element){
		rangeCheck(index);
		E oldValue = elementData(index);
		elementData[index] = element;
		return oldValue;
	}

	// TODO
	/*public boolean add(E e){

	}*/


	public void rangeCheck(int index){
		if(index >= size){
			throw new IndexOutOfBoundsException(outOfBoundMsg(index));
		}
	}

	private String outOfBoundMsg(int index){
		return "Index: " + index + ", Size:" + size;
	}
}
