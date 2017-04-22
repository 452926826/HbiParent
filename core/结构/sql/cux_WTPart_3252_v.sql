create or replace view cux_WTPart_3252_v as
select wp.IDA3VIEW,
       wp.ITERATIONIDA2ITERATIONINFO,
       wp.VERSIONIDA2VERSIONINFO,
       wp.STATESTATE,
       wp.ida2a2 ,
    wp.IDA3MASTERREFERENCE,
     wp.STATECHECKOUTINFO/*,
      wp.,
       wp.,
        wp., wp.,*/
  from plm.WTPart wp
 where wp.IDA3VIEW = 3252 --…Ëº∆ ”Õº
;
