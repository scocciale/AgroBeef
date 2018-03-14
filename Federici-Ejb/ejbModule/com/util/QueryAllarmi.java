package com.util;

public class QueryAllarmi {

	final String allarmeUno = "allarmeUno";
	final String allarmeDue = "allarmeDue";
	final String allarmeTreParteUno = "allarmeTreParteUno";
	final String allarmeQuattro = "allarmeQuattro";
	final String allarmeCinque = "allarmeCinque";
	final String allarmeSei = "allarmeSei";
	final String allarmeSette = "allarmeSette";
	final String allarmeOtto = "allarmeOtto";
	final String allarmeNove = "allarmeNove";

	// // Soggetti femmina con età superiore a 36 mesi mai partorite
	// final String allarmeUno = "SELECT
	// `ANA`.`ana_num_matricola`,`ANA`.`ana_num_matricola_madre`,`ANA`.`ana_num_matricola_padre`,
	// TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE()) "
	// + "FROM `Anagrafica` `ANA` WHERE `ANA`.`ana_ute_id`= ? AND
	// `ANA`.`ana_sesso`='F' "
	// + "AND TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())>=36 AND
	// 0="
	// + "(SELECT COUNT(*) FROM `Anagrafica` WHERE
	// `ANA`.`ana_num_matricola`=`ana_num_matricola_madre` OR
	// `ANA`.`ana_num_matricola`=`ana_num_matricola_padre`) "
	// + "ORDER BY TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// DESC";
	//
	// // Soggetti femmina con età superiore a 24 mesi mai andate in gruppi di
	// // monta
	// final String allarmeDue = "SELECT
	// `ANA`.`ana_num_matricola`,`ANA`.`ana_num_matricola_madre`,`ANA`.`ana_num_matricola_padre`,TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// "
	// + "FROM `Anagrafica` `ANA` WHERE `ANA`.`ana_ute_id`= ? AND
	// `ANA`.`ana_sesso`='F' "
	// + "AND TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())>=24 AND
	// NOT EXIST "
	// + "(SELECT `gmo_ana_id` FROM `gruppo_monta` WHERE
	// `ANA`.`ana_id`=`gmo_ana_id` GROUP BY (`gmo_ana_id`)) "
	// + "ORDER BY TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// DESC";
	//
	// // Soggetti femmina con interparto superiore a 400 giorni (differenza tra
	// le
	// // date degli ultimi due parti in carriera)
	// final String allarmeTreParteUno = "SELECT `a`.`ana_num_matricola`,"
	// + "(SELECT COUNT(*) FROM `Anagrafica` WHERE `ana_sesso`='F' AND
	// `a`.`ana_num_matricola`=`ana_num_matricola_madre`) "
	// + "FROM `Anagrafica` `a`";
	//
	// // Soggetti femmina mai inseriti in gruppi di monta negli ultimi 6 mesi
	// // (dalla data di richiesta report)
	// final String allarmeQuattro = "SELECT
	// `ANA`.`ana_num_matricola`,`ANA`.`ana_num_matricola_madre`,`ANA`.`ana_num_matricola_padre`,TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// "
	// + "FROM `Anagrafica` `ANA` WHERE `ANA`.`ana_ute_id`= ? AND
	// `ANA`.`ana_sesso`='F' "
	// + "AND TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())>=24 AND
	// NOT EXIST "
	// + "(SELECT `gmo_ana_id` FROM `gruppo_monta` WHERE
	// `ANA`.`ana_id`=`gmo_ana_id` AND TIMESTAMPDIFF( MONTH ,
	// `gmo_data_inserimento`, SYSDATE( ) ) >= 6 GROUP BY (`gmo_ana_id`)) "
	// + "ORDER BY TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// DESC";
	//
	// // Soggetti maschi con età superiore a 20 mesi non ancora macellati.
	// final String allarmeCinque = "SELECT
	// `ANA`.`ana_num_matricola`,`ANA`.`ana_num_matricola_madre`,`ANA`.`ana_num_matricola_padre`,TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// "
	// + "FROM `Anagrafica` `ANA` WHERE `ANA`.`ana_ute_id`= ? AND
	// `ANA`.`ana_sesso`='M' "
	// + "AND (`ANA`.`ana_uscita_causa`= '' OR `ANA`.`ana_uscita_causa` IS NULL)
	// "
	// + "AND TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())>=20 "
	// + "ORDER BY TIMESTAMPDIFF(MONTH,`ANA`.`ana_data_nascita`,SYSDATE())
	// DESC";
	//
	// // Soggetti maschi con resa alla macellazione inferiore al 54%.
	// (calcolare
	// // in percentuale il rapporto tra il PESO CARCASSA ed il PESO VIVO ALLA
	// // MACELLAZIONE dei soggetti maschi con causa_USCITA = MACELLO)
	// final String allarmeSei = "SELECT
	// `ana`.`ana_num_matricola`,`ana`.`ana_num_matricola_madre`,`ana`.`ana_num_matricola_padre`,((`vma`.`vma_peso_carcassa`
	// - `vma`.`vma_peso_macellazione`) / 100) "
	// + "FROM `Anagrafica` `ana`,`valutazione_mace` `vma` WHERE
	// `ana`.`ana_ute_id`= ? "
	// + "AND `ana`.`ana_sesso`='M' AND `ana`.`ana_uscita_causa`='Macellazione'
	// "
	// + "AND `ana`.`ana_id`=`vma`.`vma_ana_id` "
	// + "AND ((`vma`.`vma_peso_carcassa` - `pes`.`pes_peso`) / 100) < 54 "
	// + "ORDER BY ((`vma`.`vma_peso_carcassa` - `vma`.`vma_peso_macellazione`)
	// / 100) DESC";
	//
	// // Soggetti FEMMINE con resa alla macellazione inferiore al 50%.
	// (calcolare
	// // in percentuale il rapporto tra il PESO CARCASSA ed il PESO VIVO ALLA
	// // MACELLAZIONE dei soggetti FEMMINE con causa_USCITA = MACELLO)
	// final String allarmeSette = "SELECT
	// `ana`.`ana_num_matricola`,`ana`.`ana_num_matricola_madre`,`ana`.`ana_num_matricola_padre`,((`vma`.`vma_peso_carcassa`
	// - `vma`.`vma_peso_macellazione`) / 100) "
	// + "FROM `Anagrafica` `ana`,`valutazione_mace` `vma` WHERE
	// `ana`.`ana_ute_id`= ? "
	// + "AND `ana`.`ana_sesso`='F' AND `ana`.`ana_uscita_causa`='Macellazione'
	// "
	// + "AND `ana`.`ana_id`=`vma`.`vma_ana_id` "
	// + "AND ((`vma`.`vma_peso_carcassa` - `pes`.`pes_peso`) / 100) < 50 "
	// + "ORDER BY ((`vma`.`vma_peso_carcassa` - `vma`.`vma_peso_macellazione`)
	// / 100) DESC";
	//
	// // Soggetti maschi con INDICE SEUROP inferiore ad R ultimi 12 mesi.
	// // (individuare le matricole dei soggetti che hanno avuto una classifica
	// // della carcassa O o P della CARCASSA dei soggetti maschi presenti in
	// // anagrafica con un evento USCITA con destinazione MACELLO) (in ordine
	// di
	// // data di uscita dall’ ultimo uscito al primo degli ultimi 12 mesi )
	// final String allarmeOtto = "SELECT
	// `ana`.`ana_num_matricola`,`ana`.`ana_num_matricola_madre`,`ana`.`ana_num_matricola_padre`,`vma`.`vma_class_seurop`
	// "
	// + "FROM `Anagrafica` `ana`,`valutazione_mace` `vma` WHERE
	// `ana`.`ana_ute_id`= ? "
	// + "AND `ana`.`ana_sesso`='M' AND `ana`.`ana_uscita_causa`='Macellazione'
	// "
	// + "AND `ana`.`ana_id`=`vma`.`vma_ana_id` "
	// + "AND (`vma`.`vma_class_seurop` LIKE '%O%' OR `vma`.`vma_class_seurop`
	// LIKE '%P%') "
	// + "AND `vma`.`vma_data`>DATE_SUB(SYSDATE (),INTERVAL 12 MONTH) ORDER BY
	// `ana`.`ana_data_uscita` DESC";
	//
	// // Soggetti maschi con INDICE SEUROP inferiore ad 3 ultimi 12 mesi.
	// // (individuare le matricole dei soggetti che hanno avuto una classifica
	// // della carcassa 2 o 1 della CARCASSA dei soggetti maschi presenti in
	// // anagrafica con un evento USCITA con destinazione MACELLO) (in ordine
	// di
	// // data di uscita dall’ ultimo uscito al primo degli ultimi 12 mesi )
	// final String allarmeNove = "SELECT
	// `ana`.`ana_num_matricola`,`ana`.`ana_num_matricola_madre`,`ana`.`ana_num_matricola_padre`,`vma`.`vma_grasso_seurop`
	// "
	// + "FROM `Anagrafica` `ana`,`valutazione_mace` `vma` WHERE
	// `ana`.`ana_ute_id`= ? "
	// + "AND `ana`.`ana_sesso`='M' AND `ana`.`ana_uscita_causa`='Macellazione'
	// "
	// + "AND `ana`.`ana_id`=`vma`.`vma_ana_id` "
	// + "AND (`vma`.`vma_grasso_seurop` LIKE '%2%' OR `vma`.`vma_grasso_seurop`
	// LIKE '%1%') "
	// + "AND `vma`.`vma_data`>DATE_SUB(SYSDATE (),INTERVAL 12 MONTH) ORDER BY
	// `ana`.`ana_data_uscita` DESC";
}
