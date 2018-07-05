/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.scheduling;

import org.springframework.core.task.TaskExecutor;

/**
 * A {@link TaskExecutor} extension exposing
 * scheduling characteristics that are relevant to potential task submitters.
 *
 * <p>Scheduling clients are encouraged to submit
 * {@link Runnable Runnables} that match the exposed preferences
 * of the <code>TaskExecutor</code> implementation in use.
 *
 * @author Juergen Hoeller
 * @since 2.0
 * @see SchedulingAwareRunnable
 * @see TaskExecutor
 * @see org.springframework.scheduling.commonj.WorkManagerTaskExecutor
 */
public interface SchedulingTaskExecutor extends TaskExecutor {

	/**
	 * Does this <code>TaskExecutor</code> prefer short-lived tasks over
	 * long-lived tasks?
	 * <p>A <code>SchedulingTaskExecutor</code> implementation can indicate
	 * whether it prefers submitted tasks to perform as little work as they
	 * can within a single task execution. For example, submitted tasks
	 * might break a repeated loop into individual subtasks which submit a
	 * follow-up task afterwards (if feasible).
	 * <p>This should be considered a hint. Of course <code>TaskExecutor</code>
	 * clients are free to ignore this flag and hence the
	 * <code>SchedulingTaskExecutor</code> interface overall. However, thread
	 * pools will usually indicated a preference for short-lived tasks, to be
	 * able to perform more fine-grained scheduling.
	 * @return <code>true</code> if this <code>TaskExecutor</code> prefers
	 * short-lived tasks
	 */
	boolean prefersShortLivedTasks();

}
