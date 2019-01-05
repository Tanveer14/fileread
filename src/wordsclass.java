public class wordsclass {

    private String word;
    private int freq;

    public wordsclass() {

    }

    public wordsclass(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFreq() {
        return freq;
    }

    @Override
    public String toString() {
        return "wordsclass{" +
                "word='" + word + '\'' +
                ", freq=" + freq +
                '}';
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}
