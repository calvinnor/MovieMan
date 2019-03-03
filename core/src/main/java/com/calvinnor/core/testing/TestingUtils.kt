package com.calvinnor.core.testing

import com.calvinnor.core.dispatchers.JobDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun buildTestingDispatcher() = JobDispatcher(TestingDispatcher(), CoroutineScope(Job()))
