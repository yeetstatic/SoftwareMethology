package project;
import java.util.StringTokenizer;

/**
 * The Album class is where the user's input will be split and stored into an album data type.
 * StringTokenizer is used to split the user input into its desired sections.
 * There are three constructors to create Album based on inputs.
 * There are getter and setter methods for the Album's properties.
 * @author Manav Bali
 * @author Daniel Lopez
 *
 */
public class Album {
	private String title;
	private String artist;
	private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
	private Date releaseDate;
	private boolean isAvailable;
	
	/**
	 * Creates an instance of album given the correct parameters.
	 * @param title title of album.
	 * @param artist artist of album.
	 * @param genre genre of album.
	 * @param releaseDate releaseDate of album.
	 * @param isAvailable availability of album.
	 */
	public Album(String title, String artist, Genre genre, Date releaseDate, boolean isAvailable) {
		this.title=title;
		this.artist=artist;
		this.genre=genre;
		this.releaseDate=releaseDate;
		this.isAvailable=isAvailable;
	}
	
	/**
	 * Creates a new copy album using another album.
	 * @param album that needs to be copied.
	 */
	public Album(Album album) {
		this.title=album.title;
		this.artist=album.artist;
		this.genre=album.genre;
		this.releaseDate=album.releaseDate;
		this.isAvailable=album.isAvailable;
	}

	/**
	 * Uses StringTokenizer to divide the string into parts of an album and creates an instance of album.
	 * @param album String that contains album.
	 */
	public Album(String album) {
		StringTokenizer albumMaker= new StringTokenizer(album,",");
		int step=1;
		while((albumMaker.hasMoreTokens())){
			if(step==1){
				this.title=albumMaker.nextToken();
			}
			else if(step==2){
				this.artist=albumMaker.nextToken();
			}
			else if(step==3){
				String genre=albumMaker.nextToken();
				String firstLetter=genre.substring(0,1);
				String remaining=genre.substring(1);
				firstLetter=firstLetter.toUpperCase();
				genre=firstLetter+remaining;
				try {
					this.genre=Genre.valueOf(genre);
				}
				catch(IllegalArgumentException e) {
					this.genre=Genre.valueOf("Unknown");
				};
			}
			else if(step==4){
				this.releaseDate=new Date(albumMaker.nextToken());
				this.isAvailable=true;
			}
			step++;
		}
	}
	
	/**
	 * Takes in a object and then checks if it is an album object.
	 * Then the current instance of album then checks against the parameter object to see if they are equal albums.
	 * @param obj any object Album.
	 */
	@Override
	public boolean equals(Object obj) { 
		if(obj instanceof Album) {
			Album album=(Album) obj;
			if(album.title.equals(this.title)&&album.artist.equals(this.artist)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Grabs the current instance of album and returns it as a string.
	 * @return album as a String. 
	 */
	@Override
	public String toString() { 
		String album= this.title+"::"+this.artist+"::"+this.genre+"::"+this.releaseDate+"::";
		
		if(this.isAvailable) {
			album=album+"is available";
		}
		
		else {
			album=album+"is not available";
		}
		
		return album;
	}
	
	/**
	 * Used in CollectionManager before album is added.
	 * Sees if the album that is created has a valid date.
	 * @return boolean true if date is valid, false otherwise.
	 */
	public boolean isValid() {
		Date dates = this.releaseDate;
		return dates.isValid();
	}

	/**
	 * Used by printByDate in Collection.
	 * Comares two dates.
	 * @param album the album that is being compared.
	 * @return int -1 if left album is smaller than right album in terms of dates, 0 otherwise.
	 */
	public int compareTo(Album album) {
		int comparison=this.releaseDate.compareTo(album.releaseDate);
		return comparison;
	}
	
	/**
	 * Used by printByGenre in Collection.
	 * Comares two genres.
	 * @param album the album that is being compared.
	 * @return int -1 if left album is smaller than right album in terms of genres, 0 otherwise.
	 */
	public int compareToGenre(Album album) {
		if(this.genre.name().charAt(0)== 'C' && album.genre.name().charAt(0)== 'C') {
			if(this.genre.name().charAt(1)<album.genre.name().charAt(1)) {
				return -1;
			}
		}
		else if(this.genre.name().charAt(0)<album.genre.name().charAt(0)) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * Used by returnAlbum in Collection.
	 * @return boolean if album is successfully returned.
	 */
	public boolean returns() {
		if(this.isAvailable) {
			return false;	
		}
		this.isAvailable=true;
		return true;
	}
	
	/**
	 * Used by lendingOut in Collection.
	 * @return boolean if album is successfully lent out.
	 */
	public boolean lends() {
		if(this.isAvailable) {
			this.isAvailable=false;
			return true;	
		}
		return false;
	}
	
	/**
	 * Grabs the current instance of album and returns it as a string.
	 * @return album as a String of title and artist. 
	 */
	public String basicString() {
		return this.title+"::"+this.artist;
	}
}
