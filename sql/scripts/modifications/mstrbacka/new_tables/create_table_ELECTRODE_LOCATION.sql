CREATE TABLE "ELECTRODE_LOCATION" (
	"ELECTRODE_LOCATION_ID" INTEGER NOT NULL,
	"NAME" VARCHAR2 (150) NOT NULL,
	"SHORTCUT" VARCHAR2 (30) NOT NULL,
	"DESCRIPTION" VARCHAR2(300) NOT NULL ,
	"ELECTRODE_FIX_ID" INTEGER NOT NULL,
	"ELECTRODE_TYPE_ID" INTEGER NOT NULL,
	"IS_DEFAULT" INTEGER NOT NULL ,
PRIMARY KEY ("ELECTRODE_LOCATION_ID")
)
;
