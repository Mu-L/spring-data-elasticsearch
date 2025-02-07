/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.elasticsearch.client.elc;

import co.elastic.clients.ApiClient;
import co.elastic.clients.elasticsearch.cluster.HealthRequest;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportOptions;
import co.elastic.clients.util.ObjectBuilder;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import org.springframework.lang.Nullable;

/**
 * Reactive version of the {@link co.elastic.clients.elasticsearch.cluster.ElasticsearchClusterClient}
 *
 * @author Peter-Josef Meisch
 * @since 4.4
 */
public class ReactiveElasticsearchClusterClient
		extends ApiClient<ElasticsearchTransport, ReactiveElasticsearchClusterClient> {

	public ReactiveElasticsearchClusterClient(ElasticsearchTransport transport,
			@Nullable TransportOptions transportOptions) {
		super(transport, transportOptions);
	}

	@Override
	public ReactiveElasticsearchClusterClient withTransportOptions(@Nullable TransportOptions transportOptions) {
		return new ReactiveElasticsearchClusterClient(transport, transportOptions);
	}

	public Mono<HealthResponse> health(HealthRequest healthRequest) {
		return Mono.fromFuture(transport.performRequestAsync(healthRequest, HealthRequest._ENDPOINT, transportOptions));
	}

	public Mono<HealthResponse> health(Function<HealthRequest.Builder, ObjectBuilder<HealthRequest>> fn) {
		return health(fn.apply(new HealthRequest.Builder()).build());
	}
}
