<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <postItems>
      <items>
        <xsl:for-each select="//*[@class='box-image']//a">
          <item>
            <detailUrl>
              <xsl:value-of select="./@href"/>
            </detailUrl>
            <imageUrl>
              <xsl:value-of select="./img[1]/@src"/>
            </imageUrl>
          </item>
        </xsl:for-each>
      </items>
      <itemNames>
        <xsl:for-each select="//*[contains(@class,'name product-title')]//a">
          <item>
            <xsl:value-of select="."/>
          </item>
        </xsl:for-each>
      </itemNames>
    </postItems>
  </xsl:template>
</xsl:stylesheet>