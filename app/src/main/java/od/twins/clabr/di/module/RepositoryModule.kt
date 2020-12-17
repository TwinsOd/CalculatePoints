package od.twins.clabr.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import od.twins.clabr.data.repository.GameRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
//        @ApplicationContext applicationContext: Context,
//        gameDao: GameDao
    ): GameRepository {
        return GameRepository()
//        return GameRepository(applicationContext, gameDao)
    }

}