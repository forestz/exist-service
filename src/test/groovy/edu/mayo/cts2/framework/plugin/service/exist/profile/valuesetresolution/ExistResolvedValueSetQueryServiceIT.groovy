package edu.mayo.cts2.framework.plugin.service.exist.profile.valuesetresolution;

import static org.junit.Assert.*

import javax.annotation.Resource

import org.junit.Test

import edu.mayo.cts2.framework.model.command.Page
import edu.mayo.cts2.framework.model.core.NameAndMeaningReference
import edu.mayo.cts2.framework.model.core.ValueSetDefinitionReference
import edu.mayo.cts2.framework.model.core.ValueSetReference
import edu.mayo.cts2.framework.model.util.ModelUtils
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSet
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSetHeader
import edu.mayo.cts2.framework.plugin.service.exist.profile.BaseServiceDbCleaningBase
import edu.mayo.cts2.framework.service.profile.valuesetresolution.ResolvedValueSetReference
import edu.mayo.cts2.framework.service.profile.valuesetresolution.name.ResolvedValueSetReadId

class ExistResolvedValueSetQueryServiceIT extends BaseServiceDbCleaningBase {
	
	@Resource
	ExistResolvedValueSetQueryService query
	
	@Resource
	ExistResolvedValueSetLoaderService load
	
	@Test
	void testQuery(){
		def rvs = new ResolvedValueSet(
			resolutionInfo: new ResolvedValueSetHeader(
				resolutionOf: new ValueSetDefinitionReference(
					valueSetDefinition: new NameAndMeaningReference(content:"vsd"),
					valueSet: new ValueSetReference(content:"vs")
					)));
		
		ResolvedValueSetReference ref = load.load(rvs)
		
		def summaries = 
			query.getResourceSummaries(null, null, null, new Page())
			
		assertEquals 1, summaries.getEntries().size()

	}	
	
	@Test
	void testQueryHref(){
		def rvs = new ResolvedValueSet(
			resolutionInfo: new ResolvedValueSetHeader(
				resolutionOf: new ValueSetDefinitionReference(
					valueSetDefinition: new NameAndMeaningReference(content:"vsd"),
					valueSet: new ValueSetReference(content:"vs")
					)));
		
		ResolvedValueSetReference ref = load.load(rvs)
		
		def summaries =
			query.getResourceSummaries(null, null, null, new Page())
		
		assertNotNull summaries.getEntries().get(0).getHref()
		
		assertEquals "http://test.org/testApp/valueset/vs/definition/vsd/resolution/1", summaries.getEntries().get(0).getHref()
	}

}
