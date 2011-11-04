package edu.mayo.cts2.framework.plugin.service.exist.profile.statement;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.filter.match.StateAdjustingModelAttributeReference.StateUpdater;
import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.command.ResolvedFilter;
import edu.mayo.cts2.framework.model.command.ResolvedReadContext;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.directory.DirectoryResult;
import edu.mayo.cts2.framework.model.service.core.Query;
import edu.mayo.cts2.framework.model.statement.Statement;
import edu.mayo.cts2.framework.model.statement.StatementDirectoryEntry;
import edu.mayo.cts2.framework.plugin.service.exist.profile.AbstractExistQueryService;
import edu.mayo.cts2.framework.plugin.service.exist.profile.ResourceInfo;
import edu.mayo.cts2.framework.plugin.service.exist.restrict.directory.XpathDirectoryBuilder;
import edu.mayo.cts2.framework.plugin.service.exist.restrict.directory.XpathDirectoryBuilder.XpathState;
import edu.mayo.cts2.framework.service.profile.statement.StatementQueryService;

@Component
public class ExistStatementQueryService
	extends AbstractExistQueryService
		<Statement,
		StatementDirectoryEntry,
		edu.mayo.cts2.framework.model.service.statement.StatementQueryService,XpathState>
	implements StatementQueryService {

	@Resource
	private StatementResourceInfo statementResourceInfo;
	
	@Override
	public PredicateReference getPropertyReference(String nameOrUri) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class StatementDirectoryBuilder extends XpathDirectoryBuilder<XpathState,StatementDirectoryEntry> {

		public StatementDirectoryBuilder() {
			super(new XpathState(), new Callback<XpathState, StatementDirectoryEntry>() {

				@Override
				public DirectoryResult<StatementDirectoryEntry> execute(
						XpathState state, 
						int start, 
						int maxResults) {
					return getResourceSummaries(
							"",
							state.getXpath(), 
							start, 
							maxResults);
				}

				@Override
				public int executeCount(XpathState state) {
					throw new UnsupportedOperationException();
				}},
				
				getAvailableMatchAlgorithmReferences(),
				getAvailableModelAttributeReferences());
		}
	}

	@Override
	public DirectoryResult<StatementDirectoryEntry> getResourceSummaries(
			Query query,
			Set<ResolvedFilter> filterComponent, 
			Void restrictions,
			ResolvedReadContext readContext,
			Page page) {
		StatementDirectoryBuilder builder = new StatementDirectoryBuilder();
		
		return 
			builder.restrict(filterComponent).
				restrict(query).
				addStart(page.getStart()).
				addMaxToReturn(page.getMaxToReturn()).
				resolve();
	}

	@Override
	public DirectoryResult<Statement> getResourceList(Query query,
			Set<ResolvedFilter> filterComponent, Void restrictions, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(
			Query query, 
			Set<ResolvedFilter> filterComponent,
			Void restrictions) {
		StatementDirectoryBuilder builder = new StatementDirectoryBuilder();
		
		return 
				builder.restrict(filterComponent).
					restrict(query).
					count();
	}

	@Override
	protected List<? extends PredicateReference> getAvailablePredicateReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected StateUpdater<XpathState> getResourceNameStateUpdater() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected StatementDirectoryEntry createSummary() {
		return new StatementDirectoryEntry();
	}

	@Override
	protected StatementDirectoryEntry doTransform(Statement resource,
			StatementDirectoryEntry summary,
			org.xmldb.api.base.Resource eXistResource) {
		return summary;
	}

	@Override
	protected ResourceInfo<Statement, ?> getResourceInfo() {
		return this.statementResourceInfo;
	}

}
