package com.wawra.mvpapp.data.database.daos;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wawra.mvpapp.data.database.models.SampleModelEntity;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SampleDao_Impl implements SampleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SampleModelEntity> __insertionAdapterOfSampleModelEntity;

  public SampleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSampleModelEntity = new EntityInsertionAdapter<SampleModelEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `sample` (`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SampleModelEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
  }

  @Override
  public Single<Long> insertSampleModel(final SampleModelEntity sampleModelEntity) {
    return Single.fromCallable(new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfSampleModelEntity.insertAndReturnId(sampleModelEntity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
