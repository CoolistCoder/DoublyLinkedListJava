package main;

public class Main {
	public static void main(String[] args) {
		DoubleLinkedList linklist = new DoubleLinkedList();	//create an instance of a doubly linked list
		linklist.push_back(14);		//from here, push random data 
		linklist.push_back(26);
		linklist.push_back(11);
		linklist.push_back(44);
		linklist.push_front(566);
		linklist.push_front(66);
		linklist.push_front(87);
		linklist.push_front(221);
		linklist.insert_after(580, 4);	//insert after node 4
		linklist.delete(2);				//delete the node at 2
		linklist.pop_back();		//pop the back and front
		linklist.pop_front();
		System.out.println("printing the list");	//print the list forwards
		linklist.print();
		System.out.println("printing the list in reverse");//print the list backwards
		linklist.print_rev();
		System.out.println("The size of the list is: "+ linklist.length());
		linklist.destroy();	//destroy the list when we're done
		
		System.out.println("The program is finished");
	}

}
