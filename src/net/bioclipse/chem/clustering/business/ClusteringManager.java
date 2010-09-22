/*******************************************************************************
 * Copyright (c) 2010  Jeff Miller <jrmillerwork@gmail.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.chem.clustering.business;

import java.util.ArrayList;
import java.util.List;

import net.bioclipse.chem.clustering.clusterhandling.*;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.managers.business.IBioclipseManager;

import org.apache.log4j.Logger;

public class ClusteringManager implements IBioclipseManager {

    private static final Logger logger = Logger.getLogger(ClusteringManager.class);

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "clustering";
    }
    public ClusterList doClustering(List<IMolecule> molecules, int numClusts) throws BioclipseException
    {
    	/*
    	 * This method does default clustering of Molecules, using
    	 * algorithms present in the clusterhandling package. 
    	 */
    	logger.info("Entering doClustering");
    	
    	try {
    	ClusterList cL = new ClusterList(molecules, 
    								     new SingleLinkAlgo());
    	logger.info("Clustering list of " + cL.getSize() + "Elements");

    	cL.setDa(new MoleculeDistAlgo());
    	cL.doClustering(numClusts);
    	return cL;
    	} catch (Exception e) {
    		logger.info("Exception: " + e.getMessage());
    	}
    	return null;
    }
    public ClusterList doClustering(ClusterList cL, int numClusts) throws BioclipseException
    {
    	/*
    	 * This method does default clustering of Molecules, using
    	 * algorithms present in the clusterhandling package. 
    	 */
    	cL.setcA(new SingleLinkAlgo());
    	cL.setDa(new MoleculeDistAlgo());
    	cL.doClustering(numClusts);
    	return cL;
    }
}
