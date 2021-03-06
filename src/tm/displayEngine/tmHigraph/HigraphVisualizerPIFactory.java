//     Copyright 1998--2010 Michael Bruce-Lockhart and Theodore S. Norvell
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License. 
// You may obtain a copy of the License at 
//
//     http://www.apache.org/licenses/LICENSE-2.0 
//
// Unless required by applicable law or agreed to in writing, 
// software distributed under the License is distributed on an 
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
// either express or implied. See the License for the specific language 
// governing permissions and limitations under the License.

package tm.displayEngine.tmHigraph;

import tm.displayEngine.DisplayManager;
import tm.displayEngine.DisplayPIFactoryIntf;
import tm.interfaces.DisplayContextInterface;
import tm.plugins.Requirement;
import tm.utilities.Assert;

/**
 * 
 *	
  * @author mpbl
 */
public class HigraphVisualizerPIFactory implements DisplayPIFactoryIntf{
	
	private String configId;
	
	private HigraphVisualizerPIFactory(String parameter){
		Assert.error(parameter.contains("Higraph."),
				"Higraph displays must use parameters qualified by 'Higraph.'"); // TSN wonders why. 2011-06-24
		configId = parameter;
	}

	
    static public HigraphVisualizerPIFactory createInstance( String parameter ) {
    	return new HigraphVisualizerPIFactory(parameter) ;
    }
	
	public HigraphVisualizer createPlugin(DisplayManager dm) {		
		return new HigraphVisualizer(dm,configId);
	}

	public Requirement[] getRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getParameter() {
		return configId;
	}

	public String toString(){return "SubgraphVisualizerPiFactory("+configId+")";}

}

