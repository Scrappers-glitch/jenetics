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

import org.apache.tools.ant.filters.ReplaceTokens

/**
 * @author <a href="mailto:franz.wilhelmstoetter@gmail.com">Franz Wilhelmstötter</a>
 * @since 1.2
 * @version 4.4
 */
plugins {
	id("java-library")
}
apply plugin: 'packaging'

ext.moduleName = 'io.jenetics.example'

dependencies {
	implementation project(':jenetics')
	implementation project(':jenetics.ext')
	implementation project(':jenetics.prog')
	implementation property('include.JPX')
	implementation property('include.PRNGine')
	implementation "io.reactivex.rxjava2:rxjava:2.1.14"

	testImplementation property('include.TestNG')
}

jar.manifest.attributes('Automatic-Module-Name': 'io.jenetics.example')

packaging {
	name = property('jenetics.example.Name')
	author = property('jenetics.Author')
	url = property('jenetics.Url')
	jarjar = false
	javadoc = false

	doLast {
		copy {
			from('src/main/scripts') {
				include '**/*'
			}
			into exportScriptDir
			filter(ReplaceTokens, tokens: [
				__identifier__: identifier,
				__year__: copyrightYear,
				__version__: version
			])
		}
	}
}


