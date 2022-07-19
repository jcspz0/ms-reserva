package com.diplo.msreserva.repository;

import java.util.concurrent.Future;

public interface IUnitOfWork {
	Future<Void> Commit();
}
