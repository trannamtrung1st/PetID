<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/petBreedDTO">
    <h1 class="page-title">
      Detail information of <b>
        <span class="breed-name">
          <xsl:value-of select="name"/>
        </span>
      </b>
    </h1>
    <div class="breed-info">
      <h3>Information</h3>
      <a href="#posts">Want a pet</a>
      <div>
        <img src="{imageUrl}" class="breed-img"/>
      </div>
      <p>
        <b>Type: </b>
        <span class="type-name">
          <xsl:value-of select="typeName/name"/>
        </span>
      </p>
      <p>
        <b>Name: </b>
        <xsl:value-of select="name"/>
      </p>
      <p>
        <b>Description: </b>
        <xsl:value-of select="description"/>
      </p>
      <hr/>
      <h3>Traits</h3>
      <p>
        <xsl:for-each select="traits/item">
          <b>
            <xsl:value-of select="name"/>:
          </b>
          <xsl:value-of select="value"/>
          <br/>
        </xsl:for-each>
      </p>
      <hr/>
      <h3>Attributes</h3>
      <p>
        <xsl:for-each select="attrs/item">
          <b>
            <xsl:value-of select="name"/>:
          </b>
          <xsl:value-of select="value"/>
          <br/>
        </xsl:for-each>
      </p>
      <hr/>
      <xsl:for-each select="infos/item">
        <h3>
          <xsl:value-of select="name"/>:
        </h3>
        <p>
          <xsl:value-of select="sectionContent"/>
        </p>
      </xsl:for-each>
    </div>
    <div id="posts">
      <h1 class="page-title">
        Wanna buy
        <xsl:value-of select="name"/>?
      </h1>
      <xsl:for-each select="posts/item">
        <div class="post-item">
          <a href="{detailUrl}" target="_blank">
            <img src="{imageUrl}"/>
          </a>
          <h3>
            <a href="{detailUrl}" target="_blank">
              <xsl:value-of select="name"/>
            </a>
          </h3>
        </div>
      </xsl:for-each>
    </div>
  </xsl:template>
</xsl:stylesheet>