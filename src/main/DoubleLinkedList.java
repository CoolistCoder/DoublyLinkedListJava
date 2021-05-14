package main;
public class DoubleLinkedList {
	private class node{
		public node previous;
		public node next;
		public int data;
	}
	
	private node head;	//the start of the list
	private node end; 	//the end of the list
	private int len; //the saved length of the list
	
	private void find_end() {			//finds the end of the list and saves it
		if (this.head != null) { //while the end user wont access this, couldn't hurt...
			int counter = 1; 				//use a counter to secretly populate the data value
			node temp = this.head;			//make a traversal node to iterate through the list
			while (temp.next != null) {		//perform iteration until next node over is empty
				++counter;
				temp = temp.next;	
			}
			this.end = temp;				//save the end
			this.len = counter;				//save the count
		}
		else { //nullify the end and make sure the length reads 0 if we can't find a head
			this.len = 0;
			this.end = null;
		}
	}
	
	//insert data into the linked list
	public void push_front(int d) {
		if (this.head != null) {			//if the head is not null...
			node insert = new node();			//create a new node
			insert.next = this.head;			//make the new node point to the head
			insert.previous = null;			//make the previous node empty
			
			this.head.previous = insert;		//the node prior to the head is now the new node
			this.head.previous.data = d;	//populate the node behind the head with data
			this.head = insert;				//the head becomes the new node
		}
		else {
			this.head = new node();			//if there is no head node give it a node
			this.head.data = d;				//give the head node data
			this.head.next = null;
			this.head.previous = null;
		}
		this.find_end();				//set the end
	}
	
	public void push_back(int d) {
		if (this.head != null) {			//if the head is not null...
			node insert = new node();		//create a new node
			insert.data = d;				//populate the new node with the data we need
			node temp = this.head;			//create a traversal node and set it to the head
			while (temp.next != null) {		//iterate through each node until the next node is null
				temp = temp.next;			//iterate forward
			}
			temp.next = insert;				//the next node at the end is the new node
			insert.previous = temp;			//the previous node is attached to the new node
		}
		else {							//if the head is null...
			this.head = new node();		//simply make it a new node
			this.head.data = d;
		}
		this.find_end();			//set the end
	}
	
	public void print() {
		node temp = this.head;					//create a traversal node
		if (temp == null) {						//if the head is empty...
			System.out.println("List is empty"); //inform us
			return;
		}
		else {
			while (temp != null) {				//if the head is not empty...
				System.out.println(temp.data);	//iterate through the list
				temp = temp.next;				
			}
		}
	}
	
	public void print_rev() {					//print out the list starting from the end
		node temp = this.head;					//create a traversal node
		if (temp == null) {						//if the head is empty, just confirm
			System.out.println("List is empty");
			return;
		}
		else {			//if the head is not empty...
			temp = end;							//retrieve the end node to begin there
			while (temp != null) {				//iterate through our list in reverse
				System.out.println(temp.data);
				temp = temp.previous;
			}
		}
	}
	
	public int length() {	//returns the number of elements in the list
		if (this.head == null) return 0; //always return 0 if the head is null!
		return this.len;	//otherwise return the number of elements
	}
	
	public void pop_front() {	//pop data from the front end of the list
		if (this.head != null) {
			if (this.head.next == null) {	//nullify the head if no other elements exist
				this.head = null;
				this.len = 0;
			}
			else {
				this.head = this.head.next;	//just set the head forwards by one
				this.head.previous = null;	//the garbage collector handles this in Java
				this.find_end();
			}
		}
	}
	
	public void pop_back() {	//pop data from the end of the list
		if (this.head != null) {
			if (this.end.previous == null) {	//nullify the head if no other elements exist
				this.head = null;
				this.len = 0;
			}
			else {
				this.end = this.end.previous; //just set the end backwards by one
				this.end.next = null;		//the garbage collector handles this in Java
				--this.len;				//size is reduced by 1
			}
		}
	}
	
	public void destroy() {	//just destroy the entire list
		if (this.head != null)				//if our head is not null...
		while (this.head.next != null) {	//we just iterate through each node
			this.head = this.head.next;		//we take the head node, and set it to the next node
			this.head.previous = null;		//then we nullify the last node in the list
		}
		this.head = null;					//the head will be nullified at the end regardless
		//unlike C/C++, the garbage collector will collect any data not being used
		this.len = 0;
	}
	
	public void delete(int at) {
		if (this.head != null) {					//check if the head is empty
			node temp = this.head;					//if the head is not empty, create our traversal node
			int counter = 0;						//use a counter to track where we delete data
			
			if (at == 0) {							//if our position is 0, just pop the front and leave
				this.pop_front();
				return;
			}
			if (at < this.length() && at > -1) { //if our position is valid...
				while (counter < at) {			//iterate to the at value
					temp = temp.next;			
					++counter;					//use this as our limit
				}
				temp.previous.next = temp.next;	//set the previous node's next to the next node
				temp.next.previous = temp.previous;
				this.find_end();
			}
			else {
				if (at < 0) {this.pop_front();} //pop the front if we're below 0
				else {this.pop_back();}			//pop the back if we're out of range
				
			}
			this.find_end();					//to prevent our saved values from being affected, find the end again
		}
	}
	
	public void insert_after(int d, int at) {		//inserts data into a specific position
		if (this.head != null) {				//check to see if the head is null first
			node temp = this.head;				//if the head isn't null, create a traversal node
			int counter = 0;					//use a counter to limit our iteration
			
			if (temp != null) {					//if we don't have a null head
				if (at < 0) at = 0;			//prevent our condition from being too small or big
				if (at >= len-1) {
					this.push_back(d);
					return;
				}
				while (counter < at) {		//then iterate until counter == at
					temp = temp.next;
					++counter;
				}
				node newnode = new node();	//create a new node once we're at where we want to be
				newnode.data = d;			//populate the new node with data
				newnode.next = temp.next;	//link the new node to the next node
				temp.next.previous = newnode;	//link the next node's previous node to the new node
				newnode.previous = temp;		//the newnode points back to the current position
				temp.next = newnode;		//the current node's next connector connects to the new node
			}
			this.find_end();

		}
	}
	
	public int get(int at) {
		if (this.head != null) {					//check if the head is empty
			node temp = this.head;					//if the head is not empty, create our traversal node
			int counter = 0;						//use a counter to track where we retrieve data
			
			if (at == 0) {							//if our position is 0, just return the head
				return this.head.data;
			}
			if (at < this.length() && at > -1) { //if our position is valid...
				while (counter < at) {			//iterate to the at value
					temp = temp.next;			
					++counter;					//use this as our limit
				}
				return temp.data;			//return the data where our current node is
			}
			else {
				if (at < 0) {return this.head.data;}	//return the head node's data if < 0
				else {return this.end.data;}			//otherwise return the last node's data we can get to
			}
		}
		return -999;							//return -999 as a failsafe
	}

	
	DoubleLinkedList(){
		this.head = null; //nullify the head
		this.end = null; //nullify the end
		this.len = 0; //set the length to 0 because there is no length...
	}
	
}
