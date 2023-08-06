package algonquin.cst2335.god;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

@Entity(tableName = "images")
public class ImageEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "width")
    private int width;

    @ColumnInfo(name = "height")
    private int height;

    // Constructors
    public ImageEntity(@NonNull String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    // Getters and Setters
    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
