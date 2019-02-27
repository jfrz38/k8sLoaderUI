package interface_pkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteScript {

	public ExecuteScript() {
		
		try {
			String target = new String("/home/jf/JoseF/test.sh");
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(target);
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			System.out.println("### " + output);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}
}
