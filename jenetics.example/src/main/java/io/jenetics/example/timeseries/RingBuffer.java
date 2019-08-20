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
package io.jenetics.example.timeseries;

public class RingBuffer <T> {

	private final Object[] _buffer;

	private int _cursor = -1;
	private int _size = 0;

	public RingBuffer (final int size) {
		if (size < 1) {
			throw new IllegalArgumentException(
				"Buffer size must be a positive value"
			);
		}

		_buffer = new Object[size];
	}

	public void add (final T sample) {
		_buffer[next()] = sample;
	}

	private int next() {
		_cursor = (_cursor + 1)%_buffer.length;
		if (_size < _buffer.length) ++_size;
		return _cursor;
	}

	public Object[] snapshot() {
		final Object[] result = new Object[_size];

		if (_size < _buffer.length) {
			System.arraycopy(_buffer, 0, result, 0, _size);
		} else {
			System.arraycopy(
				_buffer, _cursor + 1,
				result, 0, _buffer.length - _cursor - 1
			);
			System.arraycopy(
				_buffer, 0,
				result, _buffer.length - _cursor - 1, _cursor + 1
			);
		}

		return result;
	}

	public int size () {
		return _buffer.length;
	}
}