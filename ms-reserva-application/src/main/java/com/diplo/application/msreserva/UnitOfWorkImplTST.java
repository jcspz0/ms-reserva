package com.diplo.application.msreserva;

import com.diplo.msreserva.repository.IUnitOfWork;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class UnitOfWorkImplTST implements IUnitOfWork {

	@Override
	public Future<Void> Commit() {
		return CompletableFuture.completedFuture(null);
	}
}
