package com.diplo.application.msreserva;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.diplo.msreserva.repository.IUnitOfWork;

public class UnitOfWorkImplTST implements IUnitOfWork{

	@Override
	public Future<Void> Commit() {
		return CompletableFuture.completedFuture(null);
	}

}
