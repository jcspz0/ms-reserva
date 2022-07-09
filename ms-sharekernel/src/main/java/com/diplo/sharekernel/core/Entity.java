package com.diplo.sharekernel.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.diplo.sharekernel.core.IBussinesRule.IBussinessRule;

public abstract class Entity<TId> {
	
	protected TId Id;
	private Collection<DomainEvent> _domainEvents;

	protected Entity()
    {
        _domainEvents = new ArrayList<DomainEvent>();
    }
	
	public Collection<DomainEvent> getDomainEvents() {
		return _domainEvents;
	}
	
	public void AddDomainEvent(DomainEvent evento)
    {
        _domainEvents.add(evento);
    }

    public void ClearDomainEvents()
    {
        _domainEvents.clear();
    }

	public TId getId() {
		return Id;
	}

	protected void setId(TId id) {
		Id = id;
	}
	
	protected void CheckRule(IBussinessRule rule) throws BussinessRuleValidationException
    {
        if(rule == null)
        {
            throw new IllegalArgumentException("Rule cannot be null");
        }
        if (!rule.IsValid())
        {
            throw new BussinessRuleValidationException(rule);
        }
    }
	

}
