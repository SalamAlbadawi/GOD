package algonquin.cst2335.god;





import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert
    long insert(ImageEntity imageEntity);

    @Update
    void update(ImageEntity imageEntity);

    @Delete
    void delete(ImageEntity imageEntity);

    @Query("SELECT * FROM images")
    List<ImageEntity> getAllImages();



    @Query("SELECT * FROM images WHERE url = :url")
    ImageEntity findByUrl(String url);

    @Query("DELETE FROM images")
    void deleteAll();
}
