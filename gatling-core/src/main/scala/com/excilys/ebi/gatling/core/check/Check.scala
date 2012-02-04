/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.gatling.core.check

import com.excilys.ebi.gatling.core.check.extractor.ExtractorFactory
import com.excilys.ebi.gatling.core.check.extractor.Extractor
import com.excilys.ebi.gatling.core.session.Session

/**
 * This class represents a Check
 *
 * @param what the function that returns the expression representing what the check should look for
 * @param how the extractor that will be used by the Check
 * @param saveAs the session attribute that will be used to store the extracted value
 * @param strategy the strategy used to perform the Check
 * @param expected the expected value of what has been found
 */
abstract class Check[T, X](val what: Session => String, val how: ExtractorFactory[T, X], val strategy: CheckStrategy[X], val saveAs: Option[String]) {
	
	def resolve(s: Session, extractor: Extractor[_]) = new ResolvedCheck(what(s), extractor.asInstanceOf[Extractor[X]], strategy, saveAs)
}
