package de.danri;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@RunWith(JUnitParamsRunner.class)
public class JVMScriptEngineTest {


    private static final ScriptEngineManager ENGINE_MANAGER =
            new ScriptEngineManager();
    private static final String ENGINE_INFO = "this is the programming " +
            "language \\'%s\\' with the engine \\'%s\\' on the jvm";


    @Test
    /**
     * js - nashorn engine of the jdk
     * ruby - jruby(http://jruby.org/)
     * python - jython(http://www.jython.org/)
     * Renjin - 'R' interpreter(http://www.renjin.org/)
     */
    @Parameters({
            "js|print('%s');",
            "ruby|puts '%s'",
            "python|print('%s')",
            "Renjin|print(\"%s\")"})
    public void testScriptEngines(String name, String outputCode) throws Exception {
        ScriptEngine scriptEngine = ENGINE_MANAGER.getEngineByName(name);
        String info = String.format(ENGINE_INFO,
                scriptEngine.getFactory().getLanguageName(),
                scriptEngine.getFactory().getEngineName());
        scriptEngine.eval(String.format(outputCode, info));
    }
}
