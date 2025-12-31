package com.employeedb.employeedatabase.data.repository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEmployeeRepository (
        impl: EmployeeRepositoryImpl
    ) : EmployeeRepository

    @Binds
    abstract fun provideAttendanceRepository (
        impl: AttendanceRepositoryImpl
    ) : AttendanceRepository
}