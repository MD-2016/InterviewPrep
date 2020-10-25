package Chapter_7.Jukebox;

import java.util.Queue;

public class Playlist
{
    private Song s;
    private Queue<Song> que;

    public Playlist(Song s, Queue<Song> que)
    {
        super();
        this.s = s;
        this.que = que;
    }

    public Song getNextSong()
    {
        return que.peek();
    }

    public void queueSong(Song s)
    {
        que.add(s);
    }

}
