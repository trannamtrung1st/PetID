<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <xsl:param name="query"/>
    <xsl:if test="count(//PetBreed[contains(translate(name,'abcdefghijklmnopqrstuvwxyz',
                                        'ABCDEFGHIJKLOMNOPQRSTUVWXYZ'),translate($query,'abcdefghijklmnopqrstuvwxyz',
                                        'ABCDEFGHIJKLOMNOPQRSTUVWXYZ'))])=0">
        <h1>Not found any result</h1>
    </xsl:if>
    <xsl:for-each select="//PetBreed[not($query) or contains(translate(name,'abcdefghijklmnopqrstuvwxyz',
                                        'ABCDEFGHIJKLOMNOPQRSTUVWXYZ'),translate($query,'abcdefghijklmnopqrstuvwxyz',
                                        'ABCDEFGHIJKLOMNOPQRSTUVWXYZ'))]">
      <div class="breed-item">
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
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>