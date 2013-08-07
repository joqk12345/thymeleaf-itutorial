/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2013, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.itutorial;

import java.io.IOException;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExerciseController {

    private static final String CHARSET = "UTF-8";

    @Autowired private ServletContext servletContext;
    @Autowired private String thymeleafVersion;

    @RequestMapping("/exercise/{index}")
    public String exercise(@PathVariable("index") Integer index, Model model) throws IOException {
        Exercise exercise = Exercise.get(index);
        String question = new ExerciseResourceLoader(servletContext, exercise).getResource("question.html", CHARSET);
        model.addAttribute("question", question);
        model.addAttribute("exercise", exercise);
        model.addAttribute("thymeleafVersion", thymeleafVersion);
        return "exercise.html";
    }
}
