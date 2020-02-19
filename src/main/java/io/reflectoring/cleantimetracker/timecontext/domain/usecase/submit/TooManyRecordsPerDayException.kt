package io.reflectoring.cleantimetracker.timecontext.domain.usecase.submit

class TooManyRecordsPerDayException : RuntimeException("only one record per day and task is allowed!")