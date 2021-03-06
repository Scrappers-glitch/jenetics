/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
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
 *
 * Author:
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmail.com)
 */
package io.jenetics.util;

import static io.jenetics.internal.util.Arrays.swap;

import io.jenetics.util.ProxySorter.Comparator;

/**
 * Implementing the index sorter with the insertion sort algorithm.
 *
 * @author <a href="mailto:franz.wilhelmstoetter@gmail.com">Franz Wilhelmstötter</a>
 * @version 5.1
 * @since 5.1
 */
final class InsertionProxySorter {

	private InsertionProxySorter() {
	}

	public static <T> int[] sort(
		final T array,
		final int length,
		final Comparator<? super T> cmp
	) {
		final int[] idx = ProxySorter.indexes(length);

		for (int i = 1; i < length; ++i) {
			int j = i;
			while (j > 0) {
				if (cmp.compare(array, idx[j - 1], idx[j]) > 0) {
					swap(idx, j - 1, j);
				} else {
					break;
				}
				--j;
			}
		}

		return idx;
	}

}
