/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.zeppelin.display.AngularObjectRegistry;
import org.apache.zeppelin.display.GUI;
import org.apache.zeppelin.interpreter.InterpreterContext;
import org.apache.zeppelin.interpreter.InterpreterContextRunner;
import org.apache.zeppelin.interpreter.InterpreterGroup;
import org.apache.zeppelin.interpreter.InterpreterResult;
import org.apache.zeppelin.shell.ShellInterpreter;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

public class ShellTest {
    public ShellTest() {
        BasicConfigurator.configure();

    }

    @Test
    public void ShellTest1() {
        Properties myproperty = new Properties();
        ShellInterpreter myShell = new ShellInterpreter(myproperty);
        myShell.open();

        InterpreterGroup intpGroup = new InterpreterGroup();
        intpGroup.add(new ShellInterpreter(myproperty));

        InterpreterContext myInterpreterContext
                = new InterpreterContext("note", "id", "title", "text", new HashMap<String, Object>(), new GUI(),
                new AngularObjectRegistry(intpGroup.getId(), null),
                new LinkedList<InterpreterContextRunner>());

        InterpreterResult myInterpreterResult = myShell.interpret("echo \"ABCDEFabcdef1234567890\"", myInterpreterContext);

        assertEquals(myInterpreterResult.message(), "ABCDEFabcdef1234567890\n");
        assertEquals(myInterpreterResult.code(), InterpreterResult.Code.SUCCESS);

    }
}
