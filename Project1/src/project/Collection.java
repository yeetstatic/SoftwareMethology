package project;

/**
 * The Collection class is what stores albums and handles features.
 * The Collection class's features include: add new albums, remove albums, lend out albums, return albums, and print albums in the collection.
 * The Collection class's print functionality includes: print albums in the collection, print albums in the collection sorted by Date, and print albums in the collection sorted by Genre.
 * @author Manav Bali
 * @author Daniel Lopez
 */

public class Collection {
	
	private Album[] albums;
	private int numAlbums;
	
	/**
	 * Looks through the albums[] array to see if the album is in the collection.
	 * @param album that is being searched for.
	 * @return index if found, -1 is not in collection.
	 */
	private int find(Album album) {
		for (int i = 0; i <= this.numAlbums-1; i++) { //works TESTED
			if(this.albums[i] != null && this.albums[i].equals(album)){		
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Increases the albums[] array by 4 without losing any albums.
	 */
	private void grow() {
		Album[] copy = new Album[this.albums.length];
		for(int i =0; i <= numAlbums-1; i++) {
			copy[i] = this.albums[i];
		}
		this.albums=new Album[this.albums.length+4];
		for(int i =0; i <= numAlbums-1; i++) {
			this.albums[i] = copy[i];
		}
	}

	/**
	 * Checks the current collection size and adds the album to the albums[] array.
	 * If the size is too small, the array size gets increased.
	 * If the size is 0 the array albums[] is initialized.
	 * @param album that needs to be added.
	 * @return true if album is added, false otherwise.
	 */
	public boolean add(Album album) {
		int currentSize = this.numAlbums;
		if (currentSize == 0) {
			this.albums=new Album[4];
			albums[0] = album;
			this.numAlbums  = 1;
			return true;
		}		
		if (currentSize == this.albums.length && find(album) == -1) {	
			 grow();
		}
		if (find(album) == -1) {
			this.albums[numAlbums] = album;
			this.numAlbums ++;
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the album is in the collection. 
	 * Removes that album and moves albums after the removed back a spot in the array.
	 * @param album that is being removed.
	 * @return true if album is removed, false otherwise.
	 */
	public boolean remove(Album album) {
		int index = find(album);
		
		if (index == -1) {
			return false;
		}
		for(int i =index; i <=  numAlbums-1; i++) {
			if(i != numAlbums-1) {
				this.albums[i] =  this.albums[i+1];	
			}
			if(i == numAlbums-1) {
				this.albums[i] = null;
			}
		}
		this.numAlbums --;
		return true;
	}
	
	/**
	 * If album is available then lending out completes and it becomes not available.
	 * If album is not available lending out does not complete.
	 * @param album that is being lent out.
	 * @return true if album is lent out, false otherwise.
	 */
	public boolean lendingOut(Album album) {
		int albumIndex=find(album);
		if(albumIndex==-1) {
			return false;
		}
		album=new Album(albums[albumIndex]);
		return album.lends();
	}
	
	/**
	 * Checks if the album is in the collection and if it is available.
	 * If album is available then return does not complete .
	 * If album is not available return completes and it becomes available.
	 * @param album that is being returned.
	 * @return true if album has been returned, false otherwise.
	 */
	public boolean returnAlbum(Album album) {
		int albumIndex=find(album);
		if(albumIndex==-1) {
			return false;
		}
		album=new Album(albums[albumIndex]);
		return album.returns();
	}

	
	/**
	 * Checks if collection is empty and if not calls prints the entire albums[] array.
	 */
	public void print() {
		if(this.numAlbums==0) {
			System.out.println("The collection is empty!");
			return;
		}
		System.out.println("*List of albums in the collection.");
		for(int counter=0; counter<albums.length; counter++) {
			if(albums[counter]!=null) {
			System.out.println(albums[counter]);
			}
		}
		System.out.println("*End of list");
	}
	
	/**
	 * Sorts albums[] by release date and prints them. 
	 */
	public void printByReleaseDate() {
		if(this.numAlbums==0) {
			System.out.println("The collection is empty!");
			return;
		}
		System.out.println("*Album collection by the release dates.");
		for(int counter=0; counter<albums.length;counter++) {
			int minIndex=counter;
			for(int secCounter=minIndex+1; secCounter<albums.length; secCounter++) {
				if(albums[secCounter]!=null&&albums[secCounter].compareTo(albums[minIndex])==-1) {
					minIndex= secCounter;
				}	
			}
			Album temp= albums[minIndex];
			albums[minIndex]=albums[counter];
			albums[counter]=temp;
		}
		for(int counter=0; counter<albums.length; counter++) {
			if(albums[counter]!=null) {
			System.out.println(albums[counter]);
			}
		}
		System.out.println("*End of list");
	}
	
	/**
	 * Sorts albums[] by Genre and prints them.
	 */
	public void printByGenre() {
		if(this.numAlbums==0) {
			System.out.println("The collection is empty!");
			return;
		}
		System.out.println("*Album collection by genre.");
		for(int counter=0; counter<albums.length;counter++) {
			int minIndex=counter;
			for(int secCounter=minIndex+1; secCounter<albums.length; secCounter++) {
				if(albums[secCounter]!=null&&albums[secCounter].compareToGenre(albums[minIndex])==-1) {
					minIndex= secCounter;
				}
			}
			Album temp= albums[minIndex];
			albums[minIndex]=albums[counter];
			albums[counter]=temp;
		}
		for(int counter=0; counter<albums.length; counter++) {
			if(albums[counter]!=null) {
			System.out.println(albums[counter]);
			}
		}
		System.out.println("*End of list");
	}

}
