<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="top"/>
    <xsl:param name="rate"/>

    <xsl:template match="/PetBreed">
        <div class="breed-item">
            <h3>
                Top <xsl:value-of select="$top"/>: <xsl:value-of select="$rate"/>% 
            </h3>
            <a href="/petid-webapp/breed/{code}">
                <img src="{imageUrl}"/>
            </a>
            <h3>
                <a href="/petid-webapp/breed/{code}">
                    <xsl:value-of select="name"/>
                </a>
            </h3>
            <h4>
                <xsl:value-of select="typeName/name"/>
            </h4>
        </div>
    </xsl:template>
</xsl:stylesheet>