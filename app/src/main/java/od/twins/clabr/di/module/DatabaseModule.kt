package od.twins.clabr.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import od.twins.clabr.data.local.ClaborDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): ClaborDatabase {
        return Room.databaseBuilder(
            appContext,
            ClaborDatabase::class.java,
            "clabor_assessment.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAssessmentDao(database: ClaborDatabase) = database.getGameDao()
}