package edu.mayo.cts2.framework.plugin.service.exist.profile.codesystem

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import edu.mayo.cts2.framework.model.codesystem.CodeSystemCatalogEntry
import edu.mayo.cts2.framework.model.service.exception.UnknownCodeSystem
import edu.mayo.cts2.framework.model.service.exception.UnknownResourceReference
import edu.mayo.cts2.framework.plugin.service.exist.profile.BaseServiceTestBaseIT
import edu.mayo.cts2.framework.model.codesystem.CodeSystemCatalogEntrySummary
import edu.mayo.cts2.framework.model.util.ModelUtils

class ExistCodeSystemServiceTestIT 
	extends BaseServiceTestBaseIT[CodeSystemCatalogEntry,CodeSystemCatalogEntrySummary] {
  
    @Autowired var readService:ExistCodeSystemReadService = null
    @Autowired var maintService:ExistCodeSystemMaintenanceService = null
 
    def getExceptionClass():Class[_<:UnknownResourceReference] = {
       classOf[UnknownCodeSystem]
    }
  
    def createResource(name:String, uri:String) = {
      var entry = new CodeSystemCatalogEntry()
      entry.setCodeSystemName(name);
      entry.setAbout(uri)
      
      maintService.createResource("", entry)
    }
      
    def getResource(name:String):CodeSystemCatalogEntry = {
    	readService.read(ModelUtils.nameOrUriFromName(name))
    }
  
    def getResourceByUri(uri:String):CodeSystemCatalogEntry = {
    	readService.read(ModelUtils.nameOrUriFromUri(uri))
    }
}