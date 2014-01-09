package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.configuration.MailContent;


/**
 * @author ebarbin
 *
 */
public interface MailContentRepository extends CrudRepository<MailContent, Long> {

	/**
     * @param avisoDirectorRegionalNoIpsParaDireccionarProcedimiento
     * @return
     */
    MailContent findOneMailContentById(Long id);

}
