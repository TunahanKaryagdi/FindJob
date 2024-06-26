package com.tunahankaryagdi.findjob.di

import com.tunahankaryagdi.findjob.data.repository.ApplicationRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.CompanyRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.JobRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.PreferredLocationRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.RecommendedRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.SkillRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.UserRepositoryImpl
import com.tunahankaryagdi.findjob.data.source.remote.ApplicationService
import com.tunahankaryagdi.findjob.data.source.remote.CompanyService
import com.tunahankaryagdi.findjob.data.source.remote.JobService
import com.tunahankaryagdi.findjob.data.source.remote.PreferredLocationService
import com.tunahankaryagdi.findjob.data.source.remote.RecommendedService
import com.tunahankaryagdi.findjob.data.source.remote.SkillService
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import com.tunahankaryagdi.findjob.domain.repository.ApplicationRepository
import com.tunahankaryagdi.findjob.domain.repository.CompanyRepository
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.domain.repository.PreferredLocationRepository
import com.tunahankaryagdi.findjob.domain.repository.RecommendedRepository
import com.tunahankaryagdi.findjob.domain.repository.SkillRepository
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService) : UserRepository{
        return UserRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideJobRepository(jobService: JobService) : JobRepository{
        return JobRepositoryImpl(jobService)
    }

    @Provides
    @Singleton
    fun provideApplicationRepository(applicationService: ApplicationService) : ApplicationRepository{
        return ApplicationRepositoryImpl(applicationService)
    }

    @Provides
    @Singleton
    fun provideSkillRepository(skillService: SkillService) : SkillRepository{
        return SkillRepositoryImpl(skillService)
    }

    @Provides
    @Singleton
    fun provideCompanyRepository(companyService: CompanyService) : CompanyRepository{
        return CompanyRepositoryImpl(companyService)
    }

    @Provides
    @Singleton
    fun providePreferredLocationRepository(preferredLocationService: PreferredLocationService) : PreferredLocationRepository{
        return PreferredLocationRepositoryImpl(preferredLocationService)
    }

    @Provides
    @Singleton
    fun provideRecommendedRepository(recommendedService: RecommendedService) : RecommendedRepository{
        return RecommendedRepositoryImpl(recommendedService)
    }
}