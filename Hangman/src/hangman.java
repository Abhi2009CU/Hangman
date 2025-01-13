
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class hangman extends PApplet {

    int lettersWrongFile;
    String currWord;
    PImage step;
    ArrayList<String> words = new ArrayList<>(List.of(
            "bridge", "marble", "guitar", "anchor", "ticket", "dragon", "forest", "planet", "circle", "desert",
            "friend", "sunset", "flower", "puzzle", "window", "castle", "spring", "pocket", "doctor", "mirror",
            "ocean", "winter", "shadow", "rocket", "island", "silver", "travel", "garden", "lantern", "fabric",
            "legend", "breezy", "mellow", "random", "active", "clever", "daring", "explore", "gentle", "huddle",
            "ignite", "jigsaw", "kindness", "laughter", "meadow", "nimble", "orbit", "palace", "quiet", "ripple",
            "sailor", "thunder", "unicorn", "velvet", "wander", "xylophone", "yearn", "zephyr", "breeze", "charm",
            "dream", "ember", "frost", "galaxy", "haven", "ignite", "joy", "karma", "lantern", "melody", "nest", "olive",
            "pebble", "quiver", "rustle", "shimmer", "thrive", "unity", "valley", "whisper", "youth", "zenith"
    ));
    
    ArrayList<Character> letters = new ArrayList<>();
    ArrayList<String> correctLetters = new ArrayList<>();
    ArrayList<String> wrongLetters = new ArrayList<>();

    public void settings(){size(600,600);}

    public void setup(){
        lettersWrongFile = 0;
        currWord = words.get((int)(Math.random()*words.size()));
        for (int i = 0; i < currWord.length(); i++) {
            letters.add(currWord.charAt(i));
            correctLetters.add(" ");
        }
        textSize(55);
    }
    public void draw() {
        step = loadImage("images/"+lettersWrongFile + ".png");
        if (!correctLetters.contains(" ")) {
            step = loadImage("images/winner.png");
        }

        step.resize(600, 600);
        image(step, 0, 0);

        for (int i = 0; letters.size() != i; i++){
            fill(0,128,0);
            text(correctLetters.get(i),150+(i*50),560);
            fill(225);
            text("_", 150+(i*50), 570);
        }

        for (int i = 0; wrongLetters.size() > i; i++){
            fill(128,0,0);
            text(wrongLetters.get(i),95* (i+1)-60,80);
        }
    }

    public static void main(String[] args) {
        PApplet.main("hangman");
    }

    public void keyReleased() {
        if (lettersWrongFile != 6) {
            if (letters.contains(key)) {
                for (int i = 0; i < currWord.length(); i++) {
                    if (currWord.charAt(i) == key) {
                        correctLetters.set(i, str(currWord.charAt(i)));
                    }
                }
            } else if (!letters.contains(key) && !wrongLetters.contains(str(key))) {
                lettersWrongFile += 1;
                wrongLetters.add(str(key));
            }
        }
    }
}
