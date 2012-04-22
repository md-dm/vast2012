/**
 * 
 */
package com.md.dm.vi.vast.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.md.dm.vi.vast.model.Meta;
import com.md.dm.vi.vast.model.MetaRepository;

/**
 * @author diego
 * 
 */
@Controller
@RequestMapping("/api/meta")
public class MetaController {

	@Inject
	private MetaRepository metaRepository;

	static final Logger logger = LoggerFactory.getLogger(MetaRepository.class);

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Iterable<Meta> findAll() {
		return metaRepository.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public final Meta get(@PathVariable("id") final String id) {
		return metaRepository.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Meta create(@RequestBody Meta todo) {
		Assert.notNull(todo);
		return metaRepository.create(todo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Meta meta, @PathVariable String id) {
		Assert.isTrue(meta.getId().equals(id));
		metaRepository.update(meta);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable String id) {
		metaRepository.remove(id);
	}
}
